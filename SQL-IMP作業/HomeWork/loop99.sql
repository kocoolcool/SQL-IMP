--�E�E���k��
create procedure loop9x9 (@v int)
as
DECLARE   @i INT = 1,
					@j int = 1,
					@str varchar(1000)
					
BEGIN
  WHILE ( @i<=@v )
    BEGIN
	set @str=''
      WHILE ( @j <=@v )
        BEGIN
		
       /*  set @str=@str+right('0'+convert(varchar(2), @j),2) 
      + '*'
      + right('0'+convert(varchar(2), @i),2) 
      + '=' 
     + right('0'+convert(varchar(2), @i*@j),2)+'  ';
    */        
	set @str += format(@j,'00') + '*' + format(@i,'00') + '=' + format(@i*@j,'00') + CHAR(9)
	SET @j+=1      
      
        END;
		print @str
     set @i+=1
     set @j=1
   END;
END;
GO

--����
execute loop9x9 9

--��X�榡
/*

1 ��0 ����k���@ 3--> 03,  12-->12 
  PRINT  RIGHT('0'+CONVERT(varchar(2), ��),2)

2 ��� i * j = i*j ����k���@  03*04=12  
  
  �Υ[���[�r���_ :  i + '*' + j + '=' + i*j

PRINT right('0'+convert(varchar(2), @i),2) 
      + '*'
      + right('0'+convert(varchar(2), @j),2) 
      + '=' 
      + right('0'+convert(varchar(2), @i*@j),2);
      

SQL 2012 �s�W format���

declare  @str varchar(150)
set @str= ''
set @str += format(@j,'00') + '*' + format(@i,'00') + '=' + format(@i*@j,'00') + CHAR(9)

*/