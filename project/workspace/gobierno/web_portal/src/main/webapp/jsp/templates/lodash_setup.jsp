
<!-- Lodash -->
<script src='https://cdn.jsdelivr.net/g/lodash@4(lodash.min.js+lodash.fp.min.js)'></script>

<script type="text/javascript">


    _.templateSettings = {
        evaluate:    /{%([\s\S]+?)%}/g,
        interpolate: /{%=([\s\S]+?)%}/g,
        escape:      /{%-([\s\S]+?)%}/g
    };

</script>

