const express = require("express");
const path = require("path");

const app = express();

app.use(express.static("./dist/tel-exchange"));

app.get("/*", (req, res) =>
  res.sendFile("index.html", { root: "dist/tel-exchange/" }),
);

app.listen(process.env.PORT || 8080);

console.log("Server started!");
console.log("For local debug use: http://localhost:8080");
