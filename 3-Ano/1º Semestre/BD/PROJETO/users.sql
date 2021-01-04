SET GLOBAL validate_password.length = 0;
SET GLOBAL validate_password.number_count = 0;
SET GLOBAL validate_password.check_user_name = false;
SET GLOBAL validate_password.mixed_case_count = 0;
SET GLOBAL validate_password.policy = "LOW";
SET GLOBAL validate_password.special_char_count = 0;

-- Criação do administrador .
CREATE USER 'admin'@'localhost';
SET PASSWORD FOR 'admin'@'localhost' = 'root';

-- Permissão de acesso a todos os objetos de todas as bases de dados em 'localhost'.
GRANT ALL PRIVILEGES ON * TO 'admin'@'localhost';

-- Criação do perfil de Funcionário.
CREATE USER 'func'@'localhost';
SET PASSWORD FOR 'func'@'localhost' = 'root';

-- Definição de previlégios para o utilizador 'funcionario'. 
-- Permissão para a execução de instruções SELECT,INSERT e UPDATE sobre a base de dados 
-- em 'localhost'.
GRANT SELECT,UPDATE,INSERT ON testeclinico.* TO 'func'@'localhost';

-- Permissão para a  execução de procedimentos .
GRANT EXECUTE ON PROCEDURE testeclinico.* TO 'func'@'localhost';

-- Criação do perfil de Profissional Saúde.
CREATE USER 'profsaude'@'localhost';
SET PASSWORD FOR 'profsaude'@'localhost' = 'root';


FLUSH PRIVILEGES;