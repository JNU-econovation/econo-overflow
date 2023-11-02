import mockUser from "./mockUser";

const mockComment = [
  {
    id: 1,
    nickname: mockUser[0].nickname,
    content: "죄송합니다.",
    createdAt: "2021-10-12",
    isAuthor: "true",
  },
  {
    id: 2,
    nickname: mockUser[1].nickname,
    content: "댓글입니다.",
    createdAt: "2021-10-12",
    isAuthor: "false",
  },
];

export default mockComment;
