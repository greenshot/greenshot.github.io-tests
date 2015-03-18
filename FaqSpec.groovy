@Grab(group='org.spockframework', module='spock-core', version='1.0-groovy-2.4')

import spock.lang.Specification

class FaqCheck extends spock.lang.Specification {

	def "all FAQs from live site should be available"() {

		def result = ''
	
		given: "current FAQ slugs"
			// for some reason, FAQs are not included in wordpress sitemap.xml - here they are:
			def slugs = '''
				is-greenshort-really-portable
				are-there-any-dependencies-to-other-software-frameworks
				why-does-windows-8-suggest-to-install-earlier-net-versions-when-starting-greenshot
				where-can-i-find-greenshots-log-file
				how-can-i-backup-my-greenshot-configuration-or-transfer-it-to-another-machine
				whenever-i-try-to-start-greenshot-i-get-a-message-an-instance-of-greenshot-is-already-running
				how-can-i-avoid-greenshot-opening-a-browser-window-at-the-end-of-the-installation-process
				where-does-greenshot-store-its-configuration-settings
				what-is-the-best-way-to-control-greenshots-configuration-at-install-time
				in-which-cases-should-i-use-the-zip-package-instead-of-the-installer
				what-exactly-does-the-exe-installer-do
				do-i-have-to-pay-for-using-greenshot
				greenshot-uses-x-mb-of-my-ram-why-is-that
				where-should-i-report-bugs
				why-does-the-print-key-not-work-in-some-windows
				how-can-i-use-the-windows-key-as-global-hotkey-for-greenshot
				how-can-i-use-greenshot-for-ocr
				is-there-any-way-to-optmize-the-png-files
				how-can-i-insert-line-breaks-in-text-boxes-when-using-greenshots-image-editor
				how-can-i-paste-screenshots-into-skype
				how-can-i-avoid-the-active-window-losing-focus-when-taking-a-screenshot
				will-there-ever-be-a-greenshot-version-for-linux-or-mac
				will-there-ever-be-a-portable-apps-version-of-greenshot
				what-can-i-do-to-support-the-development-of-greenshot
			'''
			.tokenize('\n')
			.collect{it.trim()}
		
	
		when: "we call them on our local Jekyll instance"
			slugs.each {
				result << new URL("http://localhost:4000/faq/$it/").getText()
			}
			
		
		then: "no exception should have been thrown"
			assert true
	
	}

}
