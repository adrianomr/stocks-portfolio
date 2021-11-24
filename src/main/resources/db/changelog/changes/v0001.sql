-- public.stocks_portfolio definition

-- Drop table

-- DROP TABLE public.stocks_portfolio;

CREATE TABLE IF NOT EXISTS public.stocks_portfolio (
	id int8 NOT NULL,
	user_id int8 NULL,
	CONSTRAINT stocks_portfolio_pkey PRIMARY KEY (id)
);

-- public.stocks definition

-- Drop table

-- DROP TABLE public.stocks;

CREATE TABLE IF NOT EXISTS public.stocks (
	id int8 NOT NULL,
	amount numeric(19, 2) NULL,
	price_avg numeric(19, 2) NULL,
	ticker varchar(255) NULL,
	stock_id int8 NULL,
	portfolio_id int8 NULL,
	CONSTRAINT stocks_pkey PRIMARY KEY (id)
);


-- public.stocks foreign keys


ALTER TABLE public.stocks DROP CONSTRAINT IF EXISTS fk8w9y75m4elyu5h98t6dvqmxwq;

ALTER TABLE public.stocks ADD CONSTRAINT stocks_portfolio_fkey FOREIGN KEY (portfolio_id) REFERENCES public.stocks_portfolio(id);
