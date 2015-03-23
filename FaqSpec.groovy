@Grab(group='org.spockframework', module='spock-core', version='1.0-groovy-2.4')

import spock.lang.*

class FaqCheck extends spock.lang.Specification {

	def static JEKYLL_BASE = "http://localhost:4000"

	@Shared jekyllFaqUrls = extractFaqUrlsFromSitemap("$JEKYLL_BASE/sitemap.xml")
	@Shared faqUrls = [
				"/faq/is-greenshort-really-portable/",
				"/faq/are-there-any-dependencies-to-other-software-frameworks/",
				"/faq/why-does-windows-8-suggest-to-install-earlier-net-versions-when-starting-greenshot/",
				"/faq/where-can-i-find-greenshots-log-file/",
				"/faq/how-can-i-backup-my-greenshot-configuration-or-transfer-it-to-another-machine/",
				"/faq/whenever-i-try-to-start-greenshot-i-get-a-message-an-instance-of-greenshot-is-already-running/",
				"/faq/how-can-i-avoid-greenshot-opening-a-browser-window-at-the-end-of-the-installation-process/",
				"/faq/where-does-greenshot-store-its-configuration-settings/",
				"/faq/what-is-the-best-way-to-control-greenshots-configuration-at-install-time/",
				"/faq/in-which-cases-should-i-use-the-zip-package-instead-of-the-installer/",
				"/faq/what-exactly-does-the-exe-installer-do/",
				"/faq/do-i-have-to-pay-for-using-greenshot/",
				"/faq/greenshot-uses-x-mb-of-my-ram-why-is-that/",
				"/faq/where-should-i-report-bugs/",
				"/faq/why-does-the-print-key-not-work-in-some-windows/",
				"/faq/how-can-i-use-the-windows-key-as-global-hotkey-for-greenshot/",
				"/faq/how-can-i-use-greenshot-for-ocr/",
				"/faq/is-there-any-way-to-optmize-the-png-files/",
				"/faq/how-can-i-insert-line-breaks-in-text-boxes-when-using-greenshots-image-editor/",
				"/faq/how-can-i-paste-screenshots-into-skype/",
				"/faq/how-can-i-avoid-the-active-window-losing-focus-when-taking-a-screenshot/",
				"/faq/will-there-ever-be-a-greenshot-version-for-linux-or-mac/",
				"/faq/will-there-ever-be-a-portable-apps-version-of-greenshot/",
				"/faq/what-can-i-do-to-support-the-development-of-greenshot/"
			]
			
	def "all FAQ URLs from WP site should be available on local Jekyll"() {
			
		expect: "URL to be loaded successfully"
			new URL(JEKYLL_BASE + faqUrl).getText()
			
		where:
			faqUrl << faqUrls
				
	}
	
	def "all FAQ URLs from WP site should be included in Jekylls sitemap"() {
		
		expect: "WP FAQ URL to be in Jekyll sitemap"
			jekyllFaqUrls.contains(faqUrl)
			
		where:
			faqUrl << faqUrls
			
	}
	
	def extractFaqUrlsFromSitemap(String sitemapFile) {
	
		new XmlSlurper()
				.parse(sitemapFile)
				.url.loc
				.findAll {it.text().contains("/faq/")}
				.collect {(it.text() =~ "https?://.*?/").replaceFirst("/")} // slice away protocol & host part
	}

}
