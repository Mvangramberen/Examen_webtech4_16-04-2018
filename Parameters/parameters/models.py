from django.db import models

class Author(models.Model):
	author_name = models.CharField(max_length=50)
	author_bio = models.CharField(max_length=500)

	def __unicode__(self):
		return self.author_name
	def search_quote(self, word):
		return word in self.quote_text
