DROP TABLE emp_bk;
CREATE TABLE emp_bk AS SELECT * FROM emp WHERE 1<>1;
SELECT * FROM emp_bk;
INSERT INTO emp_bk SELECT * FROM emp;
COMMIT;     -- ע��commit,�����п��ܲ�ѯ������
SELECT * FROM emp_bk;

-- Ϊʡ�£�����emp_bk��
-- ��1������������������SQL
INSERT INTO emp_bk(empno,ename,job,mgr,hiredate,sal,comm,deptno) VALUES(8888,'Newway','Developer',NULL,SYSDATE,99999,1111,50);

-- ��2���޸ĺ�ɾ������SQL
UPDATE emp_bk SET ename='Newway2',job='Mgr',mgr=1234,hiredate=to_date('2017-1-1','yyyy-mm-dd'),sal=11111,comm=9999,deptno=60 WHERE empno=8888;

DELETE FROM emp_bk WHERE empno=8888;
-- ��3��ģ����ѯ
SELECT * FROM emp_bk where ename like '%S%'

-- ��4�����������ѯ(ͬʱ����������������)
SELECT * FROM emp_bk WHERE ename='MILLER' AND job='CLERK';

SELECT * FROM emp_bk where 1=1  and ename=MILLER and job=CLERK
