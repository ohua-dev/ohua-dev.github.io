struct State {
    already_greeted: Vec<String>
}

impl State {
    pub fn new() -> State {
        State {
            already_greeted : Vec::new()
        }
    }

    fn greetings(&mut self, name:String) -> String {
        let ag = self.already_greeted.join(", ");
        let greeting = format!("Hello {}\nI already greeted all these guys: [{}]\n", name, ag);
        self.already_greeted.push(name);
        greeting
    }
}
