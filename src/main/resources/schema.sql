DROP TABLE IF EXISTS public.orders CASCADE;
DROP TABLE IF EXISTS public.items CASCADE;

CREATE TABLE public.items (
      id uuid DEFAULT gen_random_uuid() NOT NULL,
      "name" varchar NOT NULL,
      price numeric NOT NULL,
      "type" varchar NOT NULL,
      CONSTRAINT items_pk PRIMARY KEY (id)
);

CREATE TABLE public.orders (
       id uuid DEFAULT gen_random_uuid() NULL,
       item_id uuid NULL,
       price numeric NULL,
       amount numeric NOT NULL,
       CONSTRAINT orders_item_fk FOREIGN KEY (item_id) REFERENCES public.items(id) ON DELETE CASCADE ON UPDATE CASCADE
);