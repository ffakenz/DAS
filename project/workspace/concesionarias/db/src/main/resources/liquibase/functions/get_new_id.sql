create or replace if exists function get_new_id()
returns uniqueidentifier
as begin
   return (select new_id from getNewID) -- expects the view to be already created
end