---
layout: default
title: Ohua Publications
permalink: /bib/
---

{% assign pubs = site.data.bib | sort: 'date' | reverse %}
{% for p in pubs %}

### [{{ p.title }}]({{p.link}})

{{p.note}} {{ p.authors | join: ", " }}

{% if p.publication %}_{{p.publication }}_{% elsif p.institution %}_{{
p.institution }}_{% endif %}

{{ p.date }}

{% if p.abstract %}{{p.abstract}}{% endif %}

{% if p.website %}[Website]({{p.website}}){% endif %}

{% if p.paper %}[Paper]({{p.paper}}){% endif %}

{% if p.slides %}[Slides]({{p.slides}}){% endif %}
{% unless forloop.last %}
<hr width="36%">
{% endunless %}
{% endfor %}
