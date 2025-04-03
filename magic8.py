import random
name = input("Enter your name: ")
question = input("Enter your question: ")
answer = ""
random_number = random.randint(1, 9)
if random_number == 1:
  answer = "No"
elif random_number == 2:
  answer = "detects non-human entity"
elif random_number == 3:
  answer = "I don't know"
elif random_number == 4:
  answer = "Currently sleeping, ask again later"
elif random_number == 5:
  answer = "How about I ask you a question, will you get some work done?"
elif random_number == 6:
  answer = "Don't want to hurt your feelings"
elif random_number == 7:
  answer = "Ask my associates"
elif random_number == 8:
  answer = "Honestly, yeah"
else:
  answer = "Error, something happened but I don't want to tell you"

print(name + " asks: " + question)
print("Magic 8 Ball answers: " + answer)