/****** SSMS 中 SelectTopNRows 命令的指令碼  ******/

if (select roomid  from playlist where ptime='2016-12-25 13:00:00.000')='A廳' 
begin
DECLARE   @i INT = 1,@j int = 1;
DECLARE	@v_row int=(select seat_row from  m_room where roomid='A廳');
DECLARE	@v_col int=(select seat_col from  m_room where roomid='A廳');
DECLARE	@str char(5);
end;
BEGIN
  WHILE ( @i<=@v_row)
    BEGIN
	set @str=''
      WHILE ( @j <=@v_col )
        BEGIN
		insert into seats values ('2016-12-25 13:00', 1, format(@i,'00')+'-'+format(@j,'00'), '0', NULL)	
	SET @j+=1      
    END;
    set @i+=1
     set @j=1
   END;
END;
GO
 
