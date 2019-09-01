DROP VIEW IF EXISTS  v_pp_pb;
CREATE VIEW v_pp_pb AS
SELECT T1.*, T2.project_areaid,T2.project_source, T2.project_stats, T2.project_name,T2.project_address, T2.project_code,T2.area_covered FROM
( SELECT * FROM zs_project_progress WHERE project_id is NOT NULL ) T1
LEFT JOIN
( SELECT * FROM zs_project_base WHERE project_areaid is NOT NULL ) T2
ON T1.project_id = T2.id
WHERE
  T2.project_areaid is NOT NULL AND T2.project_source IS NOT NULL  AND T2.project_stats IS NOT NULL  AND T2.project_name IS NOT NULL;

DROP VIEW IF EXISTS  v_ei_ci;
CREATE VIEW v_ei_ci AS
SELECT T1.*,T2.company_address,T2.company_staff_num, T2.company_lyId, T2.company_simple_name, T2.company_areaType, T2.company_trade, T2.company_type, T2.company_size,T2.is_abovescale_enterprisess, T2.company_major, T2.company_tel
 FROM
(SELECT * FROM zs_enterp_indicators) T1
LEFT JOIN
(SELECT * FROM zs_company_info) T2
ON T1.company_id = T2.id;

DROP VIEW IF EXISTS  v_ci_ei_new;
CREATE VIEW v_ci_ei_new AS
SELECT T1.*,  T2.company_id, T2.fill_date, T2.total_cur_month, T2.total_pre_month, T2.total_cur_cumulative, T2.total_pre_cumulative, T2.add_cur_month, T2.add_pre_month, T2.add_cur_cumulative, T2.add_pre_cumulative, T2.income_cur_month, T2.income_pre_month, T2.income_cur_cumulative, T2.income_pre_cumulative, T2.profit_cur_month, T2.profit_pre_month, T2.profit_cur_cumulative, T2.profit_pre_cumulative, T2.tax_cur_month, T2.tax_pre_month, T2.tax_cur_cumulative, T2.tax_pre_cumulative, T2.receivable_cur_month, T2.receivable_pre_month, T2.receivable_cur_cumulative, T2.receivable_pre_cumulative, T2.assets_cur_month, T2.assets_pre_month, T2.assets_cur_cumulative, T2.assets_pre_cumulative, T2.bilit_cur_month, T2.bilit_pre_month, T2.bilit_cur_cumulative, T2.bilit_pre_cumulative,
T2.warter_cur_month, T2.warter_pre_month, T2.warter_cur_cumulative, T2.warter_pre_cumulative,
T2.electricity_cur_month, T2.electricity_pre_month, T2.electricity_cur_cumulative, T2.electricity_pre_cumulative,
T2.fiscal_cur_month, T2.fiscal_pre_month, T2.fiscal_cur_cumulative, T2.fiscal_pre_cumulative,
T2.cur_workers FROM
( SELECT * FROM zs_company_info where company_lyId != '' and company_lyId is not null) T1
LEFT JOIN
( select * from zs_enterp_indicators as a where fill_date = (select max(b.fill_date) from zs_enterp_indicators as b where a.company_id = b.company_id ) ) T2
ON T1.id = T2.company_id;

DROP VIEW IF EXISTS  v_pb_pp_new;
CREATE VIEW v_pb_pp_new AS
SELECT T1.*, T2.project_id, T2.fill_date, T2.current_progress, T2.increase_profit, T2.increase_work, T2.increase_sale, T2.increase_num, T2.new_output_value, T2.plant_new_output_value, T2.investment_total, T2.investment_completed, T2.investment_planned, T2.investment_completion, T2.new_or_up, T2.projec_group, T2.problems_coor, T2.project_remark, T2.reporter_tel, T2.data_reporter, T2.create_time FROM
(SELECT * FROM zs_project_base) T1
LEFT JOIN
(select * from zs_project_progress as a where fill_date = (select max(b.fill_date) from zs_project_progress as b where a.project_id = b.project_id ) ) T2
ON T1.id = T2.project_id;