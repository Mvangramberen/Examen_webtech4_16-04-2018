from django.shortcuts import render

from .models import Author
from .models import Quote
import string

def params(request, param1, param2, param3):
    return render(request, 'params/overview.html', {'list': param1 + "--" + param2 + "--" + param3})

