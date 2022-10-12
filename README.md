# Coffee_Machine
 A project I made while learning Kotlin at JetBrains Academy

This is the last Stage of the Coffe Machine Project where I learned about classes and enumerators and while that meant that I had to refactor everything I did in the previous Stage, I'm pretty happe with the end result.

Here's what I love about my end product:
- Everything is private except the input function. (this was also a goal set by the task)
- Everything should be safe for any text input without using exceptions. I just used toIntOrNull and logic gating to catch anything that doesn't make sense and giving appropriate feedback.
- The Coffee Types are dynamic and can be modified without the UI and inner workings breaking. (I have a weakness for 0 coffee types though)
- Lists every resource that is not enough to make the chosen coffee instead of just the first one.
- I think I used almost everything I just learned about classes and enums. :)

What could be better:
- fun fillRes() being not in main or the class but I see it as a neccessary evil for now and in a real setting, this would be rather about internal sensors and a person physically interacting with the machine instead of a UI interaction.
- The main UI loop not being inside the machine but unless I'm actually producing a mockup UI in some framework, I can live with it for now. Let's just think of the parts in main as button presses on the outside of the machine (I know, inconsistent with choosing a coffee type from inside the class).
- val chosen: Int = choice.toInt() - 1
  dispenseCoffee(typeList[chosen])
There's got to be a way to do dispenseCoffee(typeList[choice.toInt() - 1]) instead. Can't think of it now. Anyone?
