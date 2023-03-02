db = new Mongo().getDB("chatdb");

db.createCollection("chat", { capped: false });
db.chat.insert({ sender: "asdf", receiver: "zzz" });
db.runCommand({ convertToCapped: "chat", size: 8192 });