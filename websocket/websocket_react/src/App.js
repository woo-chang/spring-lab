import React, { useRef } from "react";

import SockJsClient from "react-stomp";

function App() {
  const $websocket = useRef(null);

  const handleMsg = (msg) => {
    console.log(msg);
  };

  const handleClickSendTo = () => {
    $websocket.current.sendMessage("/vote1");
  };

  const handleClickSendTemplate = () => {
    $websocket.current.sendMessage("/vote2");
  };

  return (
    <div>
      <SockJsClient
        url="http://localhost:8080/v1/vote"
        topics={[
          "/topics/vote1",
          "/topics/vote2",
          "/topics/votes/result",
          "/topics/votes/end",
        ]}
        onMessage={(msg) => {
          console.log(msg);
        }}
        ref={$websocket}
      />
      <button onClick={handleClickSendTo}>SendTo</button>
      <button onClick={handleClickSendTemplate}>SendTemplate</button>
    </div>
  );
}

export default App;
