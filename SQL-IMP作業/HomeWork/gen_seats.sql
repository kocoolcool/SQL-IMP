BEGIN
  DECLARE   @i INT = 1,@j int = 1;
  DECLARE	@v_row int=(select seat_row from  m_room where roomid='A廳');
  DECLARE	@v_col int=(select seat_col from  m_room where roomid='A廳');
  DECLARE	@str char(5);  
  DECLARE   @v_ptime   varchar(20); 
  DECLARE   @v_movie   int;
  DECLARE   @v_roomid  varchar(10);
  DECLARE   @x_row     int=1;
  DECLARE   @x_col     int=1;

  --設定變數值
  SET @v_ptime  = '2016-12-25 13:00';
  SET @v_movie  = 1;
  SET @v_roomid = 'A廳';
  WHILE ( @x_row <=@v_row)
    BEGIN
	set @str=''
      WHILE ( @x_col<=@v_col )
        BEGIN
		insert into seats values ('2016-12-25 13:00', 1, format(@x_row,'00')+'-'+format(@x_col,'00'), '0', NULL)	
	SET @x_col+=1      
    END;
    set @x_row+=1
     set @x_col=1
   END;
END;
GO

