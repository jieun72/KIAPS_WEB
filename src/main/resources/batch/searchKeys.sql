select
   s2.seq
from sonde s2
inner join (
select s3.nobs as nobs,
       max(s3.Pressure) as pr 
from sonde s3 
where T != -999.99
group by s3.nobs) b 
on s2.nobs = b.nobs and s2.Pressure = b.pr 
and s2.`datetime` = STR_TO_DATE('{datetime}', '%Y%m%d%H%i%s')

