-- Table Curso
CREATE TABLE IF NOT EXISTS Course (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;