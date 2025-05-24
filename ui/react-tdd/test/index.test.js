describe("Index", () => {

  it("should load without any error", () => {
    const root = document.createElement("div");
    root.setAttribute("id", "root");
    document.body.appendChild(root);
    
    const Index = require("src/index");
    //console.log(document.getElementById('root')._reactRootContainer)
    expect(document.getElementById('root')._reactRootContainer).toBeTruthy(); 
  });
});
