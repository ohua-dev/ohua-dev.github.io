class State {
    private List<String> _alreadyGreeted = new LinkedList<>();

    String greetings(String name){
        String greeting = "Hello " + name + "\nI already greeted all these guys: " + _alreadyGreeted;
        _alreadyGreeted.add(name);
        return greeting;
    }
}
