CREATE
    USER 'overflow-local'@'localhost' IDENTIFIED BY 'root';
CREATE
    USER 'overflow-local'@'%' IDENTIFIED BY 'root';

GRANT ALL PRIVILEGES ON *.* TO
    'overflow-local'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO
    'overflow-local'@'%';

-- CREATE
--     DATABASE overflow DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

