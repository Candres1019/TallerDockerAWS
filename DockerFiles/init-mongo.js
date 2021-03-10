db.createUser(
    {
        user: "admin",
        pwd: "Candres1019",
        roles: [
            {
                role: "readWrite",
                db: "ArepDockerDB"
            }
        ]
    }
)