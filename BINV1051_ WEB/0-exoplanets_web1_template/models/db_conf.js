const db = require('better-sqlite3')('C:/Users/mysti/Desktop/VINCI/BINV1051_ WEB/exoplanets.db', { verbose: console.log });
//const db = require('better-sqlite3')('C:/Users/mysti/DataGripProjects/BDJS/exoplanets_backup.db', { verbose: console.log });


module.exports = db;
