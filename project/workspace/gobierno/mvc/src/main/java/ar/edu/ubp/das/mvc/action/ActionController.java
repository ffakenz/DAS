package ar.edu.ubp.das.mvc.action;

import ar.edu.ubp.das.mvc.config.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Servlet implementation class ActionController
 */
@WebServlet("/index.jsp")
public class ActionController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActionController() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    private void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        this.setLocale(request);

        ModuleConfigImpl.load(this.getServletContext());

        String uri = request.getParameter("uri");
        uri = uri == null || uri.isEmpty() ? this.getServletContext().getInitParameter("action.default.path") : uri;

        this.processAction(request, response, uri);
    }

    private void processAction(final HttpServletRequest request, final HttpServletResponse response, final String uri) throws ServletException, IOException {
        final Map<String, ParameterConfig> parameters = new HashMap<>();
        this.processAction(request, response, uri, parameters);
    }

    private void processAction(final HttpServletRequest request, final HttpServletResponse response, final String uri, final Map<String, ParameterConfig> parameters) throws ServletException, IOException {
        String aliasUrl = null;
        String pathUrl = null;
        final String[] iuri = uri.split("/");
        if (iuri != null && iuri.length == 3) {
            aliasUrl = iuri[1];
            pathUrl = "/" + iuri[2];
        }

        final ResourceBundle bundle = ResourceBundle.getBundle(ModuleConfigImpl.getFwkPackage() + "properties.messages");

        final AliasConfig alias = ModuleConfigImpl.getAliasByName(aliasUrl);
        ForwardConfig forward = null;
        DynaActionForm form = new DynaActionForm();
        if (alias != null) {
            final ActionConfig action = alias.getActionByPath(pathUrl);
            if (action != null) {
                parameters.putAll(action.getParameters());

                final ActionMapping mapping = new ActionMapping();
                mapping.setParameters(parameters);

                final String formConfig = action.getForm();
                if (!formConfig.isEmpty()) {
                    final FormBeanConfig actionForm = alias.getFormBeanByName(formConfig);
                    if (actionForm != null) {
                        try {
                            form = DynaActionForm.class.cast(Class.forName(ModuleConfigImpl.getSrcPackage() + alias.getName() + ".forms." + actionForm.getType()).newInstance());
                        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
                            this.setMessage(ex.getMessage(), request, response);
                            forward = ModuleConfigImpl.getForwardByName("failure");
                        }
                    }
                }

                String key;
                final Enumeration<String> paramKeys = request.getParameterNames();
                while (paramKeys.hasMoreElements()) {
                    key = paramKeys.nextElement();
                    if (request.getParameterValues(key).length > 1) {
                        form.setItem(key, request.getParameterValues(key));
                    } else {
                        form.setItem(key, request.getParameter(key));
                    }
                }

                final Enumeration<String> attrKeys = request.getAttributeNames();
                while (attrKeys.hasMoreElements()) {
                    key = attrKeys.nextElement();
                    form.setItem(key, String.valueOf(request.getAttribute(key)));
                }

                final Map<String, ForwardConfig> forwards = ModuleConfigImpl.getForwards();
                forwards.putAll(action.getForwards());
                mapping.setForwards(forwards);

                try {
                    if (action.isValidate()) {
                        form.validate(mapping, request);
                    }

                    try {
                        final String actionClassName = ModuleConfigImpl.getSrcPackage() + alias.getName() + ".actions." + action.getType();

                        final Action iaction = Action.class.cast(Class.forName(actionClassName).newInstance());

                        forward = iaction.execute(mapping, form, request, response);
                        if (forward == null) {
                            if (action.isNoForward()) {
                                response.setStatus(200);
                                return;
                            } else {
                                this.setMessage(bundle.getString("TEXT.FORWARD_NULO"), request, response);
                                forward = ModuleConfigImpl.getForwardByName("failure");
                            }
                        }
                    } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
                        this.setMessage(bundle.getString("TEXT.ACTIONCLASS_INEXISTENTE"), request, response);
                        forward = ModuleConfigImpl.getForwardByName("failure");
                    }
                } catch (final SQLException | RuntimeException ex) {
                    this.setMessage(ex.getMessage() == null ? ex.toString() : alias.getResourceByName(ex.getMessage()), request, response);
                    forward = ModuleConfigImpl.getForwardByName("warning");
                }
            } else {
                this.setMessage(bundle.getString("TEXT.ACTION_INEXISTENTE"), request, response);
                forward = ModuleConfigImpl.getForwardByName("failure");
            }
        } else {
            this.setMessage(bundle.getString("TEXT.ALIAS_INEXISTENTE"), request, response);
            forward = ModuleConfigImpl.getForwardByName("failure");
        }

        this.doForward(request, response, forward, form, parameters);
    }

    private void doForward(final HttpServletRequest request, final HttpServletResponse response, final ForwardConfig forward, final DynaActionForm form, final Map<String, ParameterConfig> parameters) throws ServletException, IOException {
        if (!forward.isRedirect() && forward.getPath().indexOf(".do") > 0) {
            final Iterator<Map.Entry<String, Object>> it = form.getItems().entrySet().iterator();
            while (it.hasNext()) {
                final Map.Entry<String, Object> item = (Map.Entry<String, Object>) it.next();
                request.setAttribute(item.getKey(), item.getValue());
            }
            this.processAction(request, response, forward.getPath(), parameters);
        } else {
            response.setContentType("text/html;charset=ISO-8859-1");
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                this.gotoPage(ModuleConfigImpl.getTplPath() + forward.getPath(), request, response);
            }
        }
    }

    private void gotoPage(final String address, final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
        final RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    private void setMessage(final String message, final HttpServletRequest request, final HttpServletResponse response) {
        response.setStatus(400);
        request.setAttribute("message", message);
    }

    private void setLocale(final HttpServletRequest request) {
        final String lang = request.getParameter("lang");
        if (lang == null) {
            if (request.getSession().getAttribute("lang") == null) {
                request.getSession().setAttribute("lang", "es");
            }
        } else {
            request.getSession().setAttribute("lang", lang);
        }
        Locale.setDefault(new Locale(String.valueOf(request.getSession().getAttribute("lang"))));
    }

}
