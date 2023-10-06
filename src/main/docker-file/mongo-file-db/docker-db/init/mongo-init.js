print("CREATING USER");
db.createUser(
    {
        user: "user",
        pwd: "123",
        roles: [
            {
                role: "readWrite",
                db: "file_db"
            }
        ]
    }
);
db.createUser(
    {
        user: "admin",
        pwd: "123",
        roles: [
            {
                role: "dbOwner",
                db: "file_db"
            }
        ]
    }
);
print("LISTING USERs");
print("LISTING USERs");
print("LISTING USERs");
db.getUsers();
print(db.getUsers());
