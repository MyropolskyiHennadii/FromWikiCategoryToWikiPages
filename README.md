#From Wiki Category To Wiki Pages

This small application parses Wiki Category-page with its synonymic pages in different languages and shows all unique final pages (are not category) having geo-coordinates.
It may be useful, for instance, if you want receive all towns from category "Cities in Ukraine", their coordinates and references to wiki-pages.

You can try, for instance, with https://en.wikipedia.org/wiki/Category:English_Gothic_architecture

You can save results in json- or csv-file.
There are some 'large' categories, that's why parsing may take a time.
I've added the exception for categories with more than 5000 articles (because of the server... And I don't want organize MySQL database for keeping temporary set of articles.)

The appllication is built on StringBoot and Java-jstl. Wiki-pages are parsing with jsoup. Results are saving to json-file with json-simple.

It works here:
http://94.130.181.51:8091/wiki_category_parse
But I don't know, how long I'll pay for VPS.
