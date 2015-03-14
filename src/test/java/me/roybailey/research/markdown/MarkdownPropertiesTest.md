# MarkdownProperties Test Samples

This markdown file **actually contains a set of properties**, names and values.  But as markdown it can contain very rich documentation around those properties.

The main driver for using markdown for properties values was to allow ___readable multi-line properties___ values.

> ___"MarkdownProperties reads all code blocks from the given file as ordered pairs of names and values, so be sure only properties are included in code blocks"___



### Multiline Properties

This was the incentive for creating a simple markdown parser, to load all those database statements that would usually be found in concatenated code strings, or embedded in XML or even maybe JSon.  Markdown just seemed a much nicer way to maintain a file of database statements than XML.

##### Create a record

Here we will embed the name in some intro text about what the next property does.  The name of the property loading the create statement will be `create-record`.

By the way, we can have other stuff in here to aid documentation.
* like a list
* _because we can..._

So here is the property value, which we've defined with triple code quotes:

```CREATE bla bla bla```

##### Read a record (```read-record```)
```READ bla WHERE bla.id = {id}```

Example of name embedded in the title with value below, both using triple code quotes.

##### Update a record (`update-record`)
```
UPDATE bla
SET bla
WHERE bla.id = {id}
```

Example of name embedded in the title with multiline value below.

##### Delete a record
`delete-record`
```
DELETE bla
WHERE bla.id = {id}
```

Example of name followed immediately with multiline value below.



### Table based properties

| # | Name     | Value    | Description
|---|----------|----------|---------------------
| 1 | `first`  | `anna`   | first property
| 2 | `second` | `george` | second property
| 3 | `third`  | `sally`  | third property value



### Finally, simple properties file look-a-like

You could simply list properties in much the same way you might

`simple`     = `roger that`

`server.name`= `mighty.mike.com`

`username`   = `mickey`

