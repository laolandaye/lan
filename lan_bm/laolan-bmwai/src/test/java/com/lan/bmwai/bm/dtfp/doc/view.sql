 CREATE VIEW v_pp_pi_year AS
SELECT T1.*, T2.* FROM
( SELECT * FROM v_project_progress_year ) T1 LEFT JOIN
( SELECT project_id,Project_class,project_type,project_name,project_simple_name FROM	project_info
) T2
ON T1.project_id = T2.project_id;

 CREATE VIEW v_project_progress_year AS
 SELECT LEFT (T2.fill_date, 4) AS fill_year,T1.* FROM
( SELECT * FROM project_progress ) T1 INNER JOIN
( SELECT max(fill_date) as fill_date,project_id FROM	project_progress
 		WHERE	fill_date IS NOT NULL AND project_id IS NOT NULL GROUP BY LEFT (fill_date,4),project_id
) T2
ON T1.project_id = T2.project_id AND T1.fill_date = T2.fill_date;

 CREATE VIEW v_pp_pi_month AS
SELECT T1.*, T2.project_class AS project_class,T2.project_type,T2.project_name,T2.project_simple_name,T2.project_nature,
    T2.abroad_capital,T2.home_capital,T2.industry_type
FROM
( SELECT * FROM project_progress
	WHERE project_id IS NOT NULL AND team_code IS NOT NULL AND xzqy_dm IS NOT NULL AND company_id IS NOT NULL
 ) T1
LEFT JOIN
( SELECT * FROM project_info  ) T2
ON T1.project_id = T2.project_id

 CREATE VIEW v_pp_pi_year AS
SELECT T1.*, T2.project_class AS project_class,T2.project_type,T2.project_name,T2.project_simple_name,T2.project_nature,
        T2.abroad_capital,T2.home_capital
 FROM
( SELECT * FROM v_project_progress_year
	WHERE project_id IS NOT NULL AND team_code IS NOT NULL AND xzqy_dm IS NOT NULL AND company_id IS NOT NULL
 ) T1
LEFT JOIN
( SELECT * FROM project_info  ) T2
ON T1.project_id = T2.project_id;

CREATE VIEW v_enterp_indicators_year AS
SELECT LEFT (T2.fill_date, 4) AS fill_year,T1.* FROM
( SELECT * FROM enterp_indicators ) T1 INNER JOIN
( SELECT max(fill_date) as fill_date,company_ID FROM	enterp_indicators
		WHERE	fill_date IS NOT NULL GROUP BY LEFT (fill_date,4),company_ID
) T2
ON T1.company_ID = T2.company_ID AND T1.fill_date = T2.fill_date;