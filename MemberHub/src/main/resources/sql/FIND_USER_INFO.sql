SELECT
	COUNT(*)
FROM
	m_user
WHERE
	user_cd		=	:userId
AND	password	=	:password	