UPDATE fw_team SET area_code = '140202' WHERE	area_code = 'XZ_10';
UPDATE fw_team SET area_code = '140211' WHERE	area_code = 'XZ_11';
UPDATE fw_team SET area_code = '140212' WHERE	area_code = 'XZ_12';
UPDATE fw_team SET area_code = '140227' WHERE	area_code = 'XZ_13';
UPDATE fw_team SET area_code = '140221' WHERE	area_code = 'XZ_14';
UPDATE fw_team SET area_code = '140222' WHERE	area_code = 'XZ_15';
UPDATE fw_team SET area_code = '140223' WHERE	area_code = 'XZ_16';
UPDATE fw_team SET area_code = '140224' WHERE	area_code = 'XZ_17';
UPDATE fw_team SET area_code = '140225' WHERE	area_code = 'XZ_18';
UPDATE fw_team SET area_code = '140226' WHERE	area_code = 'XZ_19';
UPDATE fw_team SET area_code = '140203' WHERE	area_code = 'XZ_20';

UPDATE company_info SET team_code = 'SysTeam_XZ_10' WHERE	company_area = '140202';
UPDATE company_info SET team_code = 'SysTeam_T003' WHERE	company_area = '140211';
UPDATE company_info SET team_code = 'SysTeam_140212' WHERE	company_area = '140212';
UPDATE company_info SET team_code = 'SysTeam_140227' WHERE	company_area = '140227';
UPDATE company_info SET team_code = 'SysTeam_140221' WHERE	company_area = '140221';
UPDATE company_info SET team_code = 'SysTeam_140222' WHERE	company_area = '140222';
UPDATE company_info SET team_code = 'SysTeam_140223' WHERE	company_area = '140223';
UPDATE company_info SET team_code = 'SysTeam_140224' WHERE	company_area = '140224';
UPDATE company_info SET team_code = 'SysTeam_140225' WHERE	company_area = '140225';
UPDATE company_info SET team_code = 'SysTeam_140226' WHERE	company_area = '140226';
UPDATE company_info SET team_code = 'SysTeam_140203' WHERE	company_area = '140203';

SELECT * FROM
 project_progress pp WHERE NOT exists (
	select 1 from company_info where company_id=pp.company_id
)


UPDATE project_progress T
INNER JOIN
(SELECT project_id,xzqy_dm,team_code FROM project_info ) T2
ON  T2.project_id = T.project_id
SET T.xzqy_dm = T2.xzqy_dm,
 T.team_code = T2.team_code