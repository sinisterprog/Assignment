TASK
Create database for engineer
Create Spring web-based interface for CRUD operations on engineers.

Database:MySQL

The database has 3 tables, 
-Engineer
-Experience
-Qualifications

Engineer is the main for CRUD operations. Experience and Qualifications hold descriptions to populate drop-down lists in the view.
Can add 1 more table for gender.
Engineer table has a 1:1 relationship with Experience and Qualifications. 

TODO:
-Separate EngineerController into 2 files, one for list view, one for editor/new view.
-Ensure file operations work when uploading an image file.
-Figure out why unit tests have stopped working.
-Investigate type conversions between Experience and Qualification objects and their appearance on javascript form.

(Note there are a few unused files in /viewObjects and /editorsupport where I have tried to do this.
In the end I settled for a persistable Engineer object and a viewable engineer object.)
