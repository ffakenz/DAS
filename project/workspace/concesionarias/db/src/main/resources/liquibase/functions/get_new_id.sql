CREATE OR ALTER FUNCTION get_new_id()
returns uniqueidentifier
AS BEGIN
   return (SELECT new_id FROM getNewID) -- expects the view to be already created
END