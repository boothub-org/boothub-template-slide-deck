### Getting started

{{#if ghApiUsed}}
```
git clone https://github.com/{{ghProjectOwner}}/{{ghProjectId}}.git
cd {{ghProjectId}}
```
{{else}}
Download the generated zip file and unpack it. In the {{ghProjectId}} directory execute:
{{/if}}


&#8226; *On Linux or Mac OS:*
```
./gradlew build asciidoctor
```

&#8226; *On Windows:*
```
gradlew build asciidoctor
```

Open _build/asciidoc/html5/{{projectId}}.html_ in your browser to see the generated slide deck skeleton.

Change the slides by editing the file _src/docs/asciidoc/index.adoc_.

See the [template documentation](http://slide-deck.boothub.org) for more info.
