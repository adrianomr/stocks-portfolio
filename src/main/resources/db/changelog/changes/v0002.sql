
ALTER TABLE public.stocks ADD COLUMN grade NUMERIC(6,2);

CREATE SEQUENCE IF NOT EXISTS public.hibernate_sequence
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 9223372036854775807
	START 1
	CACHE 1
	NO CYCLE;