use envify_database;
drop table if exists users;
create table users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(255),
    last_name VARCHAR(255),
    first_name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    company VARCHAR(255),
    created_at TIMESTAMP default CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

DELIMITER //
CREATE TRIGGER format_email_trigger
BEFORE INSERT ON users
FOR EACH ROW
BEGIN
    SET NEW.email = LOWER(NEW.email);
END//
DELIMITER ;

drop table if exists configs;
create table configs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    description TEXT,
    user_id INT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) references users(id) ON DELETE cascade ON UPDATE cascade
);

drop table if exists favorite_configs;
create table favorite_configs (
    user_id INT NOT NULL,
    config_id INT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, config_id),
    FOREIGN KEY (user_id) references users(id) ON DELETE cascade ON UPDATE cascade,
    FOREIGN KEY (config_id) references configs(id) ON DELETE cascade ON UPDATE cascade
);

drop table if exists packages;
create table packages (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255),
    created_at TIMESTAMP default CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

drop table if exists version_status;
create table version_status (
    id INT NOT NULL AUTO_INCREMENT,
    label VARCHAR(255),
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

drop table if exists package_versions;
create table package_versions (
    id INT NOT NULL AUTO_INCREMENT,
    version_number VARCHAR(255),
    url VARCHAR(255),
    version_status_id INT NOT NULL,
    package_id INT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (version_status_id) references version_status(id) ON DELETE cascade ON UPDATE cascade,
    FOREIGN KEY (package_id) references packages(id) ON DELETE cascade ON UPDATE cascade
);

drop table if exists config_packages;
create table config_packages (
    config_id INT NOT NULL,
    package_version_id INT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    updated_at TIMESTAMP default CURRENT_TIMESTAMP,
    PRIMARY KEY (config_id, package_version_id),
    FOREIGN KEY (config_id) references configs(id) ON DELETE cascade ON UPDATE cascade,
    FOREIGN KEY (package_version_id) references package_versions(id) ON DELETE cascade ON UPDATE cascade
);