db.createUser(
  {
    user: "test",
    pwd: "test",
    roles: [
      {
        role: "readWrite",
        db: "kubbin-test",
      },
    ],
  }
);
db.roles.insertMany([
   { name: "ROLE_USER" },
   { name: "ROLE_MODERATOR" },
   { name: "ROLE_ADMIN" },
]);