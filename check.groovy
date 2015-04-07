def siteMapLocation = "http://getgreenshot.org/sitemap.xml".toURL().text

    def urlset = new XmlSlurper().parseText(siteMapLocation)
    urlset.url.each{
        println it.loc
        println it.lastmod
        println it.priority
        println "^^^^^^^^"
    }