patterns and nosql.

Used abstract factory for creating nosql and mysql daos.
Singleton classes: 
- ConnectionManager (one connection pool for all daos).
- EnrolleeCaretaker(homework about memento). Also need only one for all app.

After starting all servers (mysql, nosql and httpd) you can open localhost:27019 and going to any of database.
i.e. localhost:27019/MYSQL/enrollee (MYSQL and NOSQL in upper case, it is important) or localhost:27019/NOSQL/enrollee (also it works with all entities in app).

By default myqsl has not any enrollee but NOSQL has 2 enrollee. 


*MIGRATION*

After check go to http://localhost:27019/NOSQL/enrollee/migrate (GET) and recheck http://localhost:27019/MYSQL/enrollee. Enrollees from nosql should be appeared.


*MEMENTO*

Currently memento work only with specific id. 
go to http://localhost:27019/MYSQL/enrollee/snapshots/getAllSnapshots and check that snasphots list is empty.
After call by post http://localhost:27019/MYSQL/enrollee/update?enrolleeFirstName=andrew5&enrolleeLastName=pavlyk&enrolleeEmail=someenrolleeEmail@aomc.com&enrolleeLogin=and&enrolleePassword=somepwd&enrolleeId=1
or just change in httpd basic handler from .get to .post handler which handle /update.
After recheck snapshots http://localhost:27019/MYSQL/enrollee/snapshots/getAllSnapshots. After any update snapshot created automatically. For navigation through the snapshots there are specific endpoints:
: http://localhost:27019/MYSQL/enrollee/snapshots/current - show current position of cursor;
: http://localhost:27019/MYSQL/enrollee/snapshots/moveForward - move forward cursor through the list of snapshots
:http://localhost:27019/MYSQL/enrollee/snapshots/moveBack move back cursor.
:http://localhost:27019/MYSQL/enrollee/snapshots/reset will reset object to the CURRENT (/snapshots/current) choosed state.