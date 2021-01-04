-- Esqueleto das transações

DELIMITER //
CREATE PROCEDURE ex3(IN _NrCromo INT)
BEGIN
	DECLARE y_error BOOL default 0;
	DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET v_error = 1;
	
    SET AUTOCOMMIT = OFF;
    
    START TRANSACTION;
    
    -- BEGIN Lógica
    
    
    -- END Lógica
    
    IF(v_error) THEN
		ROLLBACK;
	END IF;
    
    COMMIT;
END//

DELIMITER ;