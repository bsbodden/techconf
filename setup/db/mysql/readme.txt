=========
Downloads
=========

MySQL Server Version is 4.1.7-nt (any newer version should work)

===================
Create the database
===================
The file "techconf-create-and-populate.sql" contains a complete SQL script to create the database and populate some initial data*

* Only for the mappings that are so far working

//
// MySQL Administrator
//
You can create the database using the MySQL Administrator tool (GUI) and selecting the "Restore" option, then clicking the "Open Backup File" button at the bottom of the screen.

-- OR --

//
// MySQL Command Line Client
//

Connect to your database, you should see something like:

|Enter password: ********
|Welcome to the MySQL monitor.  Commands end with ; or \g.
|Your MySQL connection id is 2 to server version: 4.1.7-nt
|
|Type 'help;' or '\h' for help. Type '\c' to clear the buffer.

mysql>create database techconf;
Query OK, 1 row affected (0.00 sec)

mysql> use techconf;
Database changed;

mysql> show tables;
Empty set (0.00 sec)

you can now create tables, issue queries, etc.

now you can use the "source" command or your favorite SQL tool to execute the create statements contained in one of the "techconf-*.sql" files




