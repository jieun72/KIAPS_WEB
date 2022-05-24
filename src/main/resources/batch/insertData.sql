INSERT INTO `surface_from_sonde` (
	`datetime`,
	nobs,
	ObsTime,
	lat,
	lon,
	T2m,
	RH2m
)

SELECT 
	k.`datetime`,
	k.nobs,
	k.ObsTime,
	k.lat,
	k.lon,
	k.T2m,
	k.RH2m
FROM (
	SELECT 
		s.`datetime` as `datetime`,
		s.nobs as nobs,
		a.ObsTime as ObsTime,
		s.lat as lat,
		s.lon as lon,
		s.T2m as T2m,
		s.RH2m as RH2m, 
		(6371 * acos (cos(radians(a.lat))
		* cos(radians(s.lat))
		* cos(radians(s.lon - 180) - radians(a.lon))
		+ sin(radians(a.lat))
		* sin(radians(s.lat))) ) AS distance, 
		abs(TIMESTAMPDIFF(second, s.`Date/Time`, a.ObsTime)) as diff 
	FROM surface s,
		(select
			s2.seq,
			s2.nobs,
			s2.lat,
			s2.lon-180 as lon,
			s2.ObsTime
		from sonde s2
		where s2.seq = '{seq}'
		) as a 
	where s.T2m != -999.99 
	and s.`datetime` = STR_TO_DATE('{datetime}', '%Y%m%d%H%i%s')
	and (6371 * acos (cos(radians(a.lat))
		* cos(radians(s.lat))
		* cos(radians(s.lon - 180) - radians(a.lon))
		+ sin(radians(a.lat))
		* sin(radians(s.lat))) ) < 100 
	order by distance, diff 
	limit 1 ) as k