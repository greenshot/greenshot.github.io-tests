@Grab(group='org.spockframework', module='spock-core', version='1.0-groovy-2.4')

import spock.lang.*

class FaqCheck extends spock.lang.Specification {

	def static JEKYLL_BASE = "http://localhost:4000"

	@Shared jekyllBlogUrls = extractBlogUrlsFromSitemap("$JEKYLL_BASE/sitemap.xml") 
	@Shared wpBlogUrls = extractBlogUrlsFromSitemap("sitemap.xml")


	def "all blog URLs from WP site should be available on local Jekyll"() {
			
		expect: "URL to be loaded successfully"
			new URL(JEKYLL_BASE + blogUrl).getText()
			
		where:
			blogUrl << wpBlogUrls
				
	}
	
	def "all blog URLs from WP site should be included in Jekylls sitemap"() {
		
		expect: "WP blog URL to be in Jekyll sitemap"
		println (jekyllBlogUrls)
			jekyllBlogUrls.contains(wpBlogUrl)
			
		where:
			wpBlogUrl << wpBlogUrls
			
	}
	
	def extractBlogUrlsFromSitemap(String sitemapFile) {
	
		new XmlSlurper()
				.parse(sitemapFile)
				.url.loc
				.findAll {it =~ "/\\d{4}/\\d{2}/\\d{2}/"} // our WP blog urls start with /yyyy/MM/dd pattern
				.collect {(it.text() =~ "https?://.*?/").replaceFirst("/")} // slice away protocol & host part
	}

}
