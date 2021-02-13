db.createUser(
  {
    user: "admin",
    pwd: "kubbin",
    roles: [
      {
        role: "readWrite",
        db: "kubbin",
      },
    ],
  }
);
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
]);