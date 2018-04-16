from django.conf.urls import url
from . import views

app_name = 'parameters'

urlpatterns = [

	url(r'^(?P<param1>[A-z ]+)/(?P<param2>[A-z ]+)/(?P<param3>[A-z ]+)$', views.params, name='params'),



]
