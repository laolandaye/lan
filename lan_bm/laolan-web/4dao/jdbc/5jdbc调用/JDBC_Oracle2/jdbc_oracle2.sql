SELECT * FROM emp;
-- 查询所有员工的月收入
SELECT ename,sal+nvl(comm,0) FROM emp;


-- 【Java中如何调用Oracle中的存储过程】
-- 根据输入员工的名字，获取员工的月收入（sal+comm）
-- 【1】创建一个存储过程
-- 如果报实际值过大，是因为Oracle设置问题，存储过程名缩短即可
CREATE OR REPLACE PROCEDURE getMIncomeByN(empName IN VARCHAR2,monthIncome OUT NUMBER)
AS
BEGIN
  SELECT sal+nvl(comm,0) INTO monthIncome FROM emp WHERE ename=empName;
END;


-- 【2】调用存储过程
DECLARE
   empName VARCHAR2(30) := 'ALLEN';
   monthIncome NUMBER := 0;
BEGIN
  getMIncomeByN(empName,monthIncome);
  dbms_output.put_line(empName || '月收入是：' || monthIncome);
END;

-- 【3】Java中调用Oracle存储过程



-- 【Java中如何调用自定义函数】
-- 根据员工id返回员工的姓名和职位
-- 【1】创建一个自定义函数
CREATE OR REPLACE FUNCTION fun_getNameJob(v_empno NUMBER)
RETURN VARCHAR2   -- 声明返回值类型，也不需要指定长度
AS
       empInfo VARCHAR2(50);
BEGIN
  SELECT ename||','||job INTO empInfo FROM emp WHERE empno=v_empno;
  RETURN empInfo;           -- 返回值
END;

SELECT * FROM emp;
-- 【2】测试自定义函数
SELECT empno,fun_getNameJob(empno) FROM emp WHERE empno=7369;

-- 【3】在Java中调用自定义函数
