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
