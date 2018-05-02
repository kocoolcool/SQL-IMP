--九九乘法表
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

--執行
execute loop9x9 9

--輸出格式
/*

1 補0 的方法之一 3--> 03,  12-->12 
  PRINT  RIGHT('0'+CONVERT(varchar(2), 值),2)

2 顯示 i * j = i*j 的方法之一  03*04=12  
  
  用加號加字串串起 :  i + '*' + j + '=' + i*j

PRINT right('0'+convert(varchar(2), @i),2) 
      + '*'
      + right('0'+convert(varchar(2), @j),2) 
      + '=' 
      + right('0'+convert(varchar(2), @i*@j),2);
      

SQL 2012 新增 format函數

declare  @str varchar(150)
set @str= ''
set @str += format(@j,'00') + '*' + format(@i,'00') + '=' + format(@i*@j,'00') + CHAR(9)

*/