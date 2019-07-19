# YeyaChat

A chat format plugin for Spigot with pinging and Markdown support.

## Configuration

```yaml
# file: config.yml
enable: true  # toggle plugin functionality
template: "&2%s&r: %s"  # String.format-compatible template string
ping:
  enable: true  # toggle pinging functionality
```

## Markdown

| Markup     | Description       |
|------------|-------------------|
| `__`       | <u>Underline</u>  |
| `**`       | **Bold**          |
| `*` or `_` | *Italic*          |
| `~~`       | ~~Strikethrough~~ |

Combinations allowed.

## License

```
Copyright 2019 Zachary Guard

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
