db.createUser(
    {
        user: "admin",
        pwd: "12345asd",
        roles: [
            {
                role: "readWrite",
                db: "LabArepDocker"
            }
        ]
    }
)
