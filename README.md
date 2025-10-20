# eztime-spring-boot-starter

# ⏰ EZTime — The Smarter Way to Handle Dates and Time in Java

[![](https://jitpack.io/v/<yourusername>/eztime-java.svg)](https://jitpack.io/#<yourusername>/eztime-java)
![Java](https://img.shields.io/badge/language-Java-orange)
![Spring Boot](https://img.shields.io/badge/framework-Spring%20Boot-green)
![License](https://img.shields.io/badge/license-MIT-blue)

---

## 💡 Overview

> **EZTime** is a developer-friendly, UTC-safe, and human-readable date & time library for Java and Spring Boot.  
> It simplifies timezone conversions, date math, DB-safe formatting, and human-readable differences — all through a clean, fluent API.

Built because date-time handling in Java is *powerful but unnecessarily painful*.

---

## 🚀 Quick Start

### Add dependency (via JitPack)
```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependency>
  <groupId>com.github.<yourusername></groupId>
  <artifactId>eztime-java</artifactId>
  <version>v0.3.0</version> <!-- or -SNAPSHOT while testing -->
</dependency>

Why EZTime Exists

Working with timezones, formats, and databases in Java often leads to hidden bugs and confusion.
EZTime was designed to eliminate those pain points:

Problem	EZTime Solution
Timezone conversions are verbose	EZDate.now("Asia/Kolkata").in("UTC")
String sorting breaks for non-ISO formats	Always outputs ISO-8601 UTC strings
Databases interpret timestamps differently	.toDbString() stores consistent UTC
Locale formats confuse teams (US vs rest of world)	.formatForUser(Locale locale)
Human differences not built-in	.humanDiff(from, to)
💻 Examples
Create and Format Dates
EZDate now = EZDate.now("Asia/Kolkata");
System.out.println(now.formatISO());
// → 2025-10-20T17:08:07+05:30[Asia/Kolkata]

Add Relative Time
EZDate future = now.add("2d 5h");
System.out.println(future.formatISO());
// → 2025-10-22T22:08:07+05:30[Asia/Kolkata]

Database & API Safe
String dbSafe = now.toDbString();
// → 2025-10-20T11:38:07Z

EZDate restored = EZDate.fromDbString(dbSafe);
System.out.println(restored.formatISO());
// → 2025-10-20T11:38:07Z[UTC]

Locale-Aware for Display
System.out.println(now.formatForUser(Locale.US));
// → 20 Oct 2025, 17:08 IST

⚙️ Spring Boot Integration

EZTime auto-registers as a Spring Boot starter.
You can @Autowired EZDateService directly:

@RestController
@RequestMapping("/api/time")
public class TimeController {
    private final EZDateService service;
    public TimeController(EZDateService service) {
        this.service = service;
    }

    @GetMapping("/now")
    public String now(@RequestParam(defaultValue = "UTC") String zone) {
        return service.getNow(zone);
    }
}


✅ Out of the box JSON serialization with Jackson
✅ Configurable via application.yml:

eztime:
  enabled: true

🌍 Cross-Language Vision

EZTime is designed to evolve into a multi-language library.
Upcoming SDKs:

🐍 Python: built on datetime and pytz

💻 JavaScript/TypeScript: based on Intl & luxon

🦫 Go: wraps the standard time package

☁️ EZTime Cloud API: REST-based universal time service

All SDKs will follow the same EZTime Spec — one consistent date/time standard across languages.

🧭 Roadmap

 Business day and holiday calculations

 Smart natural language parser ("next Monday at 9am")

 Built-in range utilities (range(start, end, step))

 CLI + REST API integration

 Multi-language SDKs (Python, JS, Go)

🧩 Contributing

Fork the repo

Create a feature branch: git checkout -b feature/awesome-idea

Commit your changes

Submit a pull request 🚀

We welcome contributions to bring EZTime to more ecosystems.

🧾 License

MIT License © 2025 [sriyananda7]
You’re free to use, modify, and distribute.

⭐ If EZTime saved you hours of pain — star the repo to spread the word!
